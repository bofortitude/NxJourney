

import smtplib
from email.mime.text import MIMEText

mail_to_list = ['bofei@fortinet.com']

smtp_server_domain = 'smtp.126.com'
mail_username = 'fortiadclab'
mail_password = 'Fortinet899,.'
mail_postfix = '126.com'


def send_mail(mail_to_list, subject, content):
    me = mail_username+"<"+mail_username+'@'+mail_postfix+'>'
    message = MIMEText(content)
    message['Subject'] = subject
    message['From'] = me
    message['To'] = ';'.join(mail_to_list)
    try:
        session = smtplib.SMTP()
        print ('step1')
        session.connect(smtp_server_domain)
        print('step2')
        session.login('fortiadclab@126.com', 'Fortinet899')
        print('step3')
        session.sendmail(me, mail_to_list, message.as_string())
        print('step4')
        session.close()
        print('step5')
        print('The mail has been sent over.')
    except:
        print ('Sending mail failed!')




if __name__ == '__main__':
    send_mail(['bofei@fortinet.com'], 'this is the trying information', 'hi bofei, \n this is the trying information for my account')


