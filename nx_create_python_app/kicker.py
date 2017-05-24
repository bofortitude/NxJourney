#!/usr/bin/python

from SantaClara.lib import argparse
from SantaClara.mylib.NxTalk2System import SshAgent
from SantaClara.mylib.NxLogging import setSimpleLogging
from SantaClara.mylib import EmailAgent



if __name__ == '__main__':
    setSimpleLogging()
    # EmailAgent.send_mail(['bofei@fortinet.com'], 'this is the testing mail from fortiadc lab', 'this is to notify you that the mail agent is working.')
    EmailAgent.send_mail(['bofei@fortinet.com'], 'this is the trying information', 'hi bofei, \n this is the trying information for my account')
    # my_ssh = SshAgent.SshAgent('10.106.3.61', 'root', 'fortinet')
    # my_ssh.connectRemote()
    # my_ssh.execCommand('date')
    # my_ssh.execCommand('uptime')
    # my_ssh.execCommand('ls -l')
    # my_ssh.closeSsh()


