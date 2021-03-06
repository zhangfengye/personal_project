#!/bin/bash
if [ $# -eq 1 ]
then
    cur_date=`date --date="${1}" +%Y-%m-%d`
else
#cur_date=`date +%Y-%m-%d`
cur_date=`date -d -1day +%Y-%m-%d`
fi
echo "cur_date:"${cur_date}
year=`date --date=$cur_date +%Y`
month=`date --date=$cur_date +%m`
day=`date --date=$cur_date +%d`

table_name_day=dw_pvs_day
table_name_hour=dw_pvs_hour
table_name_month=dw_pvs_month
hive_dir_day=/user/hive/warehouse/wcas.db/dw_pvs_day/
hive_dir_hour=/user/hive/warehouse/wcas.db/dw_pvs_hour/part=$cur_date/
hive_dir_month=/user/hive/warehouse/wcas.db/dw_pvs_month/
mysql_db_pwd=2480937z
mysql_db_name=root
SQOOP_HOME=/usr/sqoop

#echo 'sqoop dw_pvs_day start'
#$SQOOP_HOME/bin/sqoop export \
#--connect "jdbc:mysql://hadoop0:3306/test" \
#--username $mysql_db_name \
#--password $mysql_db_pwd \
#--table $table_name_day \
#--export-dir $hive_dir_day \
#--fields-terminated-by '\001'
#echo 'sqoop dw_pvs_day end'


#echo 'sqoop dw_pvs_month start'
#$SQOOP_HOME/bin/sqoop export \
#--connect "jdbc:mysql://hadoop0:3306/test" \
#--username $mysql_db_name \
#--password $mysql_db_pwd \
#--table $table_name_month \
#--export-dir $hive_dir_month \
#--fields-terminated-by '\001'
#echo 'sqoop dw_pvs_month end'

echo 'sqoop dw_pvs_hour start'
$SQOOP_HOME/bin/sqoop export \
--connect "jdbc:mysql://hadoop0:3306/test" \
--username $mysql_db_name \
--password $mysql_db_pwd \
--table $table_name_hour \
--export-dir $hive_dir_hour \
--fields-terminated-by '\t'
echo 'sqoop dw_pvs_hour end'

