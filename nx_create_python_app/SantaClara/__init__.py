

import sys


from SantaClara.etc.system_path import *
from SantaClara.lib import six


if six.PY2:
    sys.path.insert(0, santaclara_root_path+'/lib/ConcurrentLogHandler/py2')
else:
    sys.path.insert(0, santaclara_root_path+'/lib/ConcurrentLogHandler/py3')


sys.path.insert(0, santaclara_root_path+'/mylib')

sys.path.insert(0, santaclara_root_path+'/lib')

sys.path.insert(0, santaclara_root_path+'/myModule')

