#!/bin/bash


# echo "[--------------- ---------------]"

fortiadclab_parent_path="/root"

current_working_path=`pwd`
relative_script_path=`dirname $0`
cd $relative_script_path
script_path=`pwd`
cd ..
upper_script_path=`pwd`
cd $current_working_path

fortiadclab_www_path=$fortiadclab_parent_path"/fortiadclab_www"
fortiadclab_root_path=$fortiadclab_parent_path"/fortiadclab_www/fortiadc_lab_server"
fortiadclab_code_path=$upper_script_path"/fortiadc_lab_server"
fortiadclab_ui_code_path=$upper_script_path"/fortiadc_lab_ui"
config_template_path=$script_path"/config_template"



# git pull latest code
echo "[---------------Change directory to $script_path ---------------]"
cd $script_path
echo "[---------------Run \"git pull\" ---------------]"
git pull


# build fortiadclab-ui dist directory
echo "[---------------Change directory to $fortiadclab_ui_code_path ---------------]"
cd $fortiadclab_ui_code_path
echo "[---------------Run \"npm install\" ...---------------]"
npm install
echo "[---------------Run \"npm run build\" ... ---------------]"
npm run build



# collect static files for fortiadclab-server
echo "[---------------Change directory to $fortiadclab_code_path ---------------]"
cd $fortiadclab_code_path
echo "[---------------Run \"rm -rf collected_static\" ---------------]"
rm -rf collected_static
echo "[---------------Collect static for django project. ---------------]"
./manage.py collectstatic


# migrate database
echo "[---------------Run \"./manage.py makemigrations\" ... ---------------]"
./manage.py makemigrations
echo "[---------------Run \"./manage.py migrate\" ---------------]"
./manage.py migrate


# copy fortiadclab_ui to /root/fortiadclab_www
echo "[---------------Run \"rm -rf $fortiadclab_www_path\" ---------------]"
rm -rf $fortiadclab_www_path
echo "[---------------Run \"mkdir $fortiadclab_www_path\" ---------------]"
mkdir $fortiadclab_www_path
echo "[---------------Run \"mkdir $fortiadclab_www_path"/fortiadc_lab_ui"\" ---------------]"
mkdir $fortiadclab_www_path"/fortiadc_lab_ui"
echo "[---------------Run \"cp -rf $fortiadclab_ui_code_path"/dist" $fortiadclab_www_path"/fortiadc_lab_ui/"\" ---------------]"
\cp -rf $fortiadclab_ui_code_path"/dist" $fortiadclab_www_path"/fortiadc_lab_ui/"


# copy the fortiadclab_server to /root/
echo "[---------------Run \"rm -rf $fortiadclab_root_path\" ---------------]"
rm -rf $fortiadclab_root_path
echo "[---------------Copy file: \"cp -rf $fortiadclab_code_path $fortiadclab_parent_path/fortiadclab_www\" ---------------]"
\cp -rf $fortiadclab_code_path $fortiadclab_parent_path"/fortiadclab_www/"


# reboot the supervisord
echo "[---------------Finding supervisord pid ... ---------------]"
supervisord_pid=`ps -ef |grep supervisord|grep -v "grep"|awk '{print $2}'`
echo "[---------------Run \"kill -9 $supervisord_pid\" ---------------]"
kill -9 $supervisord_pid
echo "[---------------Enable the supervisord. ---------------]"
supervisord -c /etc/supervisord.conf
echo "[---------------Restart all supervisord programs ...---------------]"
supervisorctl -c /etc/supervisord.conf restart all


# reboot nginx
echo "[---------------Reboot nginx ... ---------------]"
systemctl restart nginx



