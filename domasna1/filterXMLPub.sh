#!/bin/bash

if [ $# -ne 1 ]
then
echo "Usage: $0 <open street map xml>"
exit 1
fi



./osmfilter $1 --keep="amenity=pub" | ./osmconvert - --all-to-nodes --csv="@id @lon @lat name" --csv-separator=',' --out-csv	
