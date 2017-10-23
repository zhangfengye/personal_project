#!/bin/bash
dir="/var/logs/nginx/access.log.2017-09-12-03.log.COMPLETED"
dirv="/var/logs/nginx/access.log.2017-09-12-03.log"
if [ -e $dir ] 
then  
mv $dir $dirv
fi
/usr/flume/bin/flume-ng agent -c conf -f /usr/flume/conf/collect-hdfs.conf -n a1 -Dflume.root.logger=INFO,console