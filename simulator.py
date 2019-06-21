import requests

import os
import json

os.environ['no_proxy'] = '127.0.0.1,localhost'

http_proxy  = "http://10.10.10.10:8080"
https_proxy = "https://10.10.10.10:8080"
ftp_proxy   = "ftp://10.10.10.10:8080"
no_proxy   = "127.0.0.1:8080"
NO_PROXY   = "127.0.0.1:8080"

proxyDict = { 
              "http"  : http_proxy, 
              "https" : https_proxy, 
              "ftp"   : ftp_proxy,
              "no_proxy": no_proxy
            }

resp = requests.get('http://localhost:8080/users', proxyDict)
if resp.status_code != 200:
    # This means something went wrong.
   print('Error when GET /users/ {}'.format(resp.status_code))

for user in resp.json():
    print(json.dumps(user, indent=4))