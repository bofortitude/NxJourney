

import os
import re
import shutil
import logging



def removeForce(path):
    '''
    Remove give path forcely.
    :param path:
    :return:
    '''
    if not os.path.exists(path):
        return True

    if os.path.isfile(path):
        try:
            os.remove(path)
            return True
        except:
            return False
    elif os.path.isdir(path):
        try:
            shutil.rmtree(path)
            return True
        except:
            return False
    else:
        return False



def walkRemovePycacheDir(rootPath):
    logging.warning('Starting to remove all the "__pycache__" directories recursively from the directory "' + str(rootPath) + '" ...')
    pycPattern = re.compile(r'__pycache__')
    for root, dirs, files in os.walk(rootPath):
        for i in dirs:
            if pycPattern.match(i):
                if removeForce(root+'/'+i) == True:
                    logging.warning('The directory "'+str(root)+'/'+str(i)+'" has been removed.')
                else:
                    logging.warning('Removing directory "' + root + '/' + i + '" meets error!')


def delPycachecDir(includeParent=True):
    originalWorkingPath= os.getcwd()
    scriptPath = os.path.split(os.path.realpath(__file__))[0]
    os.chdir(scriptPath)
    os.chdir('..')
    parrentPath = os.getcwd()
    os.chdir(originalWorkingPath)
    if includeParent:
        walkRemovePycacheDir(parrentPath)
    else:
        walkRemovePycacheDir(scriptPath)


if __name__ == '__main__':
    delPycachecDir()
