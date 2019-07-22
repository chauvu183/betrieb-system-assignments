#!/bin/bash
# printUsage gibt die Syntax für die Verwendung von try_host aus.
printUsage() {
	echo "Usage: try_host [-h|-s <sec>] <hostname>|<IP-Address>"
}

# Prüft, ob die Anzahl der Argumente gleich 1 ist.
if [[ $# -eq 1 ]]; then
	# Wenn ja, dann...
	# Prüft ob das erste Argument leer ist.
	if [[ -z $1 ]]; then
		# Wenn ja, dann...
		# Ausgabe der "Usage Message"
		printUsage
	else
		# Ansonsten...
		# Prüft ob das erste Argument gleich "-h" ist.
		if [[ $1 == "-h" ]]; then
			# Wenn ja, dann...
			# Ausgabe der "Usage Message"
			printUsage
		else
			# Ansonsten...
			# Wiederhole endlos...
			while [[ true ]]; do
				# Sendet einen einzelnen Ping an den Host / die IP-Addresse aus dem ersten Argument und verhindert die Ausgabe.
				ping -n 1 $1 >/dev/null
				# Prüft, ob der letzte ausgeführte Befehl erfolgreich war.
				if [[ $? -eq 0 ]]; then
					# Wenn ja, dann...
					# Ausgabe des Host's/IP-Addresse mit dem Vermerk "OK".
					echo $1 "OK"
				else
					# Ansonsten...
					# Ausgabe des Host's/IP-Addresse mit dem Vermerk "FAILED".
					echo $1 "FAILED"
				fi
				# Wartet 10 Sekunden.
				sleep 10
			done
		fi
	fi
elif [[ $# -eq 3 ]]; then
	# Ansonsten, wenn die Anzahl gleich 3 ist...
	# Prüft, ob das erste Argument leer ist.
	if [[ -z $1 ]]; then
		# Wenn ja, dann...
		# Ausgabe der "Usage Message"
		printUsage
	else
		# Ansonsten...
		# Prüft, ob das zweite Argument leer ist.
		if [[ -z $2 ]]; then
			# Wenn ja, dann...
			# Ausgabe der "Usage Message"
			printUsage
		else
			# Ansonsten...
			# Prüft ob das dritte Argument leer ist.
			if [[ -z $3 ]]; then
				# Wenn ja, dann...
				# Ausgabe der "Usage Message"
				printUsage
			else
				# Ansonsten...
				# Prüft, ob das erste Argument gleich "-s" ist.
				if [[ $1 == "-s" ]]; then
					# Wenn ja, dann...
					# Regulärer Ausdruck für Zahlen von 0-9 mit mindestens einer Ziffer.
					re='^[0-9]+$'
					# Prüft, ob das zweite Argument dem regulären Ausdruck entspricht.
					if [[ $2 =~ $re ]]; then
						# Wenn ja, dann...
						# Wiederhole endlos...
						while [[ true ]]; do
							# Sendet einen einzelnen Ping an den Host / die IP-Addresse aus dem dritten Argument und verhindert die Ausgabe.
							ping -n 1 $3 >/dev/null
							# Prüft, ob der letzte ausgeführte Befehl erfolgreich war.
							if [[ $? -eq 0 ]]; then
								# Wenn ja, dann...
								# Ausgabe des Host's/IP-Addresse mit dem Vermerk "OK".
								echo $3 "OK"
							else
								# Ansonsten...
								# Ausgabe des Host's/IP-Addresse mit dem Vermerk "FAILED".
								echo $3 "FAILED"
							fi
							# Wartet die Anzahl an Sekunden die im zweiten Argument übergeben wurde.
							sleep $2
						done
					else
						# Ansonsten...
						# Ausgabe der "Usage Message"
						printUsage
					fi
				else
					# Ansonsten...
					# Ausgabe der "Usage Message"
					printUsage
				fi
			fi
		fi
	fi
else
	# Ansonsten...
	# Ausgabe der "Usage Message"
	printUsage
fi
