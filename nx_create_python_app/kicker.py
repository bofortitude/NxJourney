#!/usr/bin/python

from SantaClara.lib import argparse
from SantaClara.mylib.NxTalk2System import SshAgent
from SantaClara.mylib.NxLogging import setSimpleLogging



if __name__ == '__main__':
    setSimpleLogging()
    my_ssh = SshAgent.SshAgent('10.106.3.61', 'root', 'fortinet')
    my_ssh.connectRemote()
    my_ssh.execCommand('date')
    my_ssh.execCommand('uptime')
    my_ssh.execCommand('ls -l')
    my_ssh.closeSsh()

