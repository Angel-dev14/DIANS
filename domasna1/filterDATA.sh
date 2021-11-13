#!/bin/bash

echo "id,lon,lat,name" > $1

awk -v file="$1" -F ',' '{if ($4!="") printf("%s\r\n",$0) >> file }'