// Einbinden externer Bibliotheken.

#include <stdio.h>
#include <limits.h>
#include <fcntl.h>
#include <string.h>

// Einstiegspunkt für mkfile.
int main () {
	// Der Dateiname.
	char filename[30];
	// Die Länge des Dateinames.
	int filename_length;
	// Ausgabe der Zeichenkette "Name der neuen Datei: ".
	printf("Name der neuen Datei: ");
	// Liest die ersten 30 Zeichen, einschließlich des 
	// Zeilenumbruchs die der Benutzer eingibt.
	fgets(filename, 31, stdin);
	// Ermittelt die Länge des eingegebenen Dateinames.
	filename_length = strlen(filename);
	// Prüft ob das letzte Zeichen des Dateinames ein 
	// Zeilenumbruch ist.
	if( filename[filename_length-1] == '\n' ) {
		// Wenn ja, dann...
		// Das letzte Zeichen aus dem Dateinamen wird entfernt.
		filename[filename_length-1] = 0;
	}
	// Erstellt eine leere Datei mit Lese-/Schreib-/Ausführberechtigung 
	// für den Benutzer.
	// Anschließend wird geprüft ob bei dem Erstellen der Datei 
	// ein Fehler aufgetreten ist.
	if (creat(filename, 0700) == -1)
	{
		// Wenn ja, dann...
		// Gibt eine Fehlermeldung für den Fehlerfall aus.
		printf("Die Datei %s wurde nicht erfolgreich angelegt!\n", filename);
		// Gibt eine -1 für den Fehlerfall aus.
		return -1;
	} else {
		// Gibt eine Meldung für den Erfolgsfall aus.
		printf("Die Datei %s wurde erfolgreich angelegt!\n", filename);
	}
	// Gibt eine 0 für den Erfolgsfall aus.
	return 0;
}
