#!/bin/bash

./filterXMLFastFood.sh skopje.osm | ./filterDATA.sh fast_foods.csv
./filterXMLPub.sh skopje.osm | ./filterDATA.sh pubs.csv