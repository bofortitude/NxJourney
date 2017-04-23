#!/bin/bash


# echo "[--------------- ---------------]"

fortiadclab_www_parent_path="/root"
fortiadclab_www_directory_name="fortiadclab_www"

#############################################################################################

current_working_path=`pwd`
relative_script_path=`dirname $0`
cd $relative_script_path
script_path=`pwd`
cd ..
upper_script_path=`pwd`
cd $current_working_path

#fortiadclab_www_path=$fortiadclab_www_parent_path"/fortiadclab_www"
fortiadclab_www_path=$fortiadclab_www_parent_path"/"$fortiadclab_www_directory_name
fortiadc_lab_server_root_path=$fortiadclab_www_path"/fortiadc_lab_server"
fortiadc_lab_server_code_path=$upper_script_path"/fortiadc_lab_server"
fortiadc_lab_ui_code_path=$upper_script_path"/fortiadc_lab_ui"
config_template_path=$script_path"/config_template"

replaced_fortiadc_lab_server_path_in_config=$fortiadclab_www_parent_path"/"$fortiadclab_www_directory_name"/fortiadc_lab_server"

# mkdir root path
echo "[---------------Run \"rm -rf $fortiadclab_www_path\" ---------------]"
rm -rf $fortiadclab_www_path
echo "[---------------Run \"mkdir $fortiadclab_www_path\" ---------------]"
mkdir $fortiadclab_www_path


# git pull latest code
echo "[---------------Change directory to $script_path ---------------]"
cd $script_path
echo "[---------------Run \"git pull\" ---------------]"
git pull


# build fortiadclab-ui dist directory
echo "[---------------Change directory to $fortiadc_lab_ui_code_path ---------------]"
cd $fortiadc_lab_ui_code_path
echo "[---------------Run \"npm install\" ...---------------]"
npm install
echo "[---------------Run \"npm run build\" ... ---------------]"
npm run build



# collect static files for fortiadclab-server
echo "[---------------Change directory to $fortiadc_lab_server_code_path ---------------]"
cd $fortiadc_lab_server_code_path
echo "[---------------Run \"rm -rf collected_static\" ---------------]"
rm -rf collected_static
echo "[---------------Collect static for django project. ---------------]"
./manage.py collectstatic


# migrate database
echo "[---------------Change directory to $fortiadc_lab_server_code_path ---------------]"
cd $fortiadc_lab_server_code_path
echo "[---------------Run \"./manage.py makemigrations\" ... ---------------]"
./manage.py makemigrations
echo "[---------------Run \"./manage.py migrate\" ---------------]"
./manage.py migrate


# copy fortiadc_lab_ui to /root/fortiadclab_www
echo "[---------------Run \"mkdir $fortiadclab_www_path"/fortiadc_lab_ui"\" ---------------]"
mkdir $fortiadclab_www_path"/fortiadc_lab_ui"
echo "[---------------Run \"cp -rf $fortiadc_lab_ui_code_path"/dist" $fortiadclab_www_path"/fortiadc_lab_ui/"\" ---------------]"
\cp -rf $fortiadc_lab_ui_code_path"/dist" $fortiadclab_www_path"/fortiadc_lab_ui/"


# copy the fortiadclab_server to /root/
echo "[---------------Run \"rm -rf $fortiadc_lab_server_root_path\" ---------------]"
rm -rf $fortiadc_lab_server_root_path
echo "[---------------Copy file: \"cp -rf $fortiadc_lab_server_code_path $fortiadclab_www_path/\" ---------------]"
\cp -rf $fortiadc_lab_server_code_path $fortiadclab_www_path"/"


# copy uwsgi.ini to fortiadc_lab_server root
echo "[---------------Run \"cp $config_template_path"/uwsgi.ini" $fortiadc_lab_server_root_path"/"\" ---------------]"
\cp $config_template_path"/uwsgi.ini" $fortiadc_lab_server_root_path"/"
echo "[---------------Replace path string in uwsgi.ini ---------------]"
sed -i "s%/root/fortiadclab_www/fortiadc_lab_server%$replaced_fortiadc_lab_server_path_in_config%g" $fortiadc_lab_server_root_path"/uwsgi.ini"

# reboot the supervisord
echo "[---------------Run cp $config_template_path"/supervisord.conf" /etc/ ---------------]"
\cp $config_template_path"/supervisord.conf" /etc/
echo "[---------------Replace path string in /etc/supervisord.conf ---------------]"
sed -i "s%/root/fortiadclab_www/fortiadc_lab_server%$replaced_fortiadc_lab_server_path_in_config%g" /etc/supervisord.conf
echo "[---------------Finding supervisord pid ... ---------------]"
supervisord_pid=`ps -ef |grep supervisord|grep -v "grep"|awk '{print $2}'`
echo "[---------------Run \"kill -9 $supervisord_pid\" ---------------]"
kill -9 $supervisord_pid
echo "[---------------Enable the supervisord. ---------------]"
supervisord -c /etc/supervisord.conf
echo "[---------------Restart all supervisord programs ...---------------]"
supervisorctl -c /etc/supervisord.conf restart all


# reboot nginx
echo "[---------------Run cp $config_template_path"/nginx.conf" /etc/nginx/ ---------------]"
\cp $config_template_path"/nginx.conf" /etc/nginx/
echo "[---------------Run rm -rf /etc/nginx/sites-enabled/default ---------------]"
rm -rf /etc/nginx/sites-enabled/default
echo "[---------------Run rm -rf /etc/nginx/sites-available/default ---------------]"
rm -rf /etc/nginx/sites-available/default
echo "[---------------Run cp $config_template_path/default /etc/nginx/sites-available/ ---------------]"
\cp $config_template_path/default /etc/nginx/sites-available/
echo "[---------------Replace path string in /etc/nginx/sites-available/default ---------------]"
sed -i "s%/root/fortiadclab_www/fortiadc_lab_server%$replaced_fortiadc_lab_server_path_in_config%g" /etc/nginx/sites-available/default
echo "[---------------Run ln -s /etc/nginx/sites-available/default /etc/nginx/sites-enabled/ ---------------]"
ln -s /etc/nginx/sites-available/default /etc/nginx/sites-enabled/
echo "[---------------Reboot nginx ... ---------------]"
systemctl restart nginx


#sed 's%/root/fortiadclab_www/fortiadc_lab_server%/newpath%g'
