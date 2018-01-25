"# Observit-Assignment" 

Detta program tar emot inställningar för en videokamera, validerar inställningarna, justerar dem till närmast tillåtna värde och returnerar de nya inställningarna.

Dessa inställningar valideras:
Bildrutor/sek(FPS): 2, 4, 8,...30
Kvalitét: 5, 10, 15,...80
Upplösningar: 800x600, 800x450, 640x480, 480x300, 176x144

Det inmatade värdet jämförs med en lista över tillåtna värden för den specifika typen (t.ex. Bildrutor/sek). Den väljer ut det närmaste värdet. Om det inmatade värdet har samma differens mellan två värden, så väljs det tillåtna värde som är störst. Ett exempel: Tillåtna värden är 2 och 4, inmatat värde är 3, så differensen i absolut värde är lika stort. Då väljs det större av de två tillåtna värdena, d.v.s. 4.

Upplösning valideras i stort sett likadant som FPS och kvalitét, men eftersom det är två värden som valideras, alltså bredd i pixlar, samt höjd i pixlar, så blir det lite svårare att säga hur valideringen ska ske.
Det antagande som görs i den här implementationen är att i de flesta upplösningarna så är höjden mellan 56-81% av bredden. 
Det är förutsägbart; det finns alltså ingen upplösning som är t.ex.: 10000x50, 300x900 eller 666x111. 
Eftersom de flesta upplösningarna är som en kvadrat som ligger ner på långsidan, så kan man förenkla valideringen av upplösningen genom att multiplicera höjd och bredd för den inmatade upplösningen, samt de tillåtna upplösningarna. Sedan jämförs det som vanligt och det närmaste tillåtna värdet väljs, eller det högsta värdet om avståndet mellan två värden är likadana.