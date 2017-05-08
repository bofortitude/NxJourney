#!/usr/bin/python


from SantaClara.mylib import remove_pyc
from SantaClara.mylib import remove_pycache
from SantaClara.etc import system_path
from SantaClara.mylib.NxLogging import setSimpleLogging







if __name__ == '__main__':
    setSimpleLogging()
    remove_pyc.walkRemovePyc(system_path.project_root_path)
    remove_pycache.walkRemovePycacheDir(system_path.project_root_path)


