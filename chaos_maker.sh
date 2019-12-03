#!/bin/bash
list=$(cat << EOF
rennesgo_back_1 
rennesgo_back_2 
rennesgo_front_1
rennesgo_front_2
EOF
)

if [ "$1" != -r ]; then
	kill=$(echo "$list" | sort -R | head -n1)
	echo killing $kill
	docker container stop $kill
	echo killed $kill
else
	for i in `echo "$list"`; do
		echo restarting $i
		docker container start $i
		echo restarted $i
	done
fi
