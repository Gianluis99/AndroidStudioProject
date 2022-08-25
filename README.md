# AndroidStudioProject

# Informazioni
App Gestionale per gestire le prenotazioni di uno studio.

# Funzionalità 
* Aggiungere una prenotazione.
* Rimuovere una prenotazione.
* Calendario prenotazioni.
* Notifiche giornaliere prenotazioni.
* Liste clienti.
* Database info clienti.


#Altre informazioni

## Activity
* Mobile-navigation è un Navigation Resource File e  ci permette di andare a navigare fra i fragment.

* I fragment sono delle pagine che è possibile aprire grazie al menu scorrevole,
posto sulla sinistra.

*Per permettere ai fragment di essere scorsi, dobbiamo inseririre l'apposito bottone
nel menu tramite activity_main_drawer.

*Inoltre dobbiamo andare nel navigation ed inserire il fragment, mettendo in String l'apposita stringa
che va a identificare un fragment.








## Aggiungere un activity:
*un Activity deve essere dichiarata nel manifest.


## Manifest:
* nel manifest è possibile dichiarare tutte le activity dell'application.
* è possibile cambiare nome dell'activity(titolo.)

## Colori:

*per cambiare i colori  andare in theme e per esempio
qualora si volessa cambiare i colori del Primary basta inserire questa riga xml
  <item name="colorPrimary">@color/teal_700</item>.

* color button bisgna fare backgroundTint

* per fare una sfumatura bisogna dichiarare un file drawable e poi dichiarare i colori
come da esempio:
```
  <item>
        <shape>
            <gradient
                android:startColor="#ff2d9a59"
                android:centerColor="#ff42959a"
                android:endColor="#ff23729a"
                android:angle="135"/>
        </shape>
    </item>
</selector>
```
