#!/bin/bash
# printUsage gibt die Syntax für die Verwendung von frename aus.
printUsage() {
	echo "Usage: frename <string>"
}
# Prüft ob die Anzahl der übergebenen Argumente gleich 1 ist.
if [[ $# -eq 1 ]]; then
	# Wenn ja, dann...
	# Prüft ob das erste Argument leer ist.
	if [[ -z "$1" ]]; then
		# Wenn ja, dann...
		# Ausgabe der "Usage Message".
		printUsage
	else
		# Ansonsten...
		# Iteration durch alle Dateien im aktuellen Verzeichnis.
		for file in *; do
			# Extrahiert den Dateinamen ohne Dateiendung, der Datei aus dem aktuellen Iterationsschritt.
			filename="${file%.*}"
			# Extrahiert die Dateiendung ohne Dateinamen, der Datei aus dem aktuellen Iterationsschritt.
			extension="${file#*.}"
			# die Datei aus dem aktuellen Iterationsschritt wird umbenannt.
			# Der neue Dateiname setzt sich zusammen aus dem Dateinamen + dem ersten Argument + der Dateiendung.
			mv $file $filename$1" llloool  ".$extension
		done
	fi
else
	# Ansonsten...
	# Ausgabe der "Usage Message"
	printUsage
fi
