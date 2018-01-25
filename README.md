"# Observit-Assignment" 

Detta program tar emot inst�llningar f�r en videokamera, validerar inst�llningarna, justerar dem till n�rmast till�tna v�rde och returnerar de nya inst�llningarna.

Dessa inst�llningar valideras:
Bildrutor/sek(FPS): 2, 4, 8,...30
Kvalit�t: 5, 10, 15,...80
Uppl�sningar: 800x600, 800x450, 640x480, 480x300, 176x144

Det inmatade v�rdet j�mf�rs med en lista �ver till�tna v�rden f�r den specifika typen (t.ex. Bildrutor/sek). Den v�ljer ut det n�rmaste v�rdet. Om det inmatade v�rdet har samma differens mellan tv� v�rden, s� v�ljs det till�tna v�rde som �r st�rst. Ett exempel: Till�tna v�rden �r 2 och 4, inmatat v�rde �r 3, s� differensen i absolut v�rde �r lika stort. D� v�ljs det st�rre av de tv� till�tna v�rdena, d.v.s. 4.

Uppl�sning valideras i stort sett likadant som FPS och kvalit�t, men eftersom det �r tv� v�rden som valideras, allts� bredd i pixlar, samt h�jd i pixlar, s� blir det lite sv�rare att s�ga hur valideringen ska ske.
Det antagande som g�rs i den h�r implementationen �r att i de flesta uppl�sningarna s� �r h�jden mellan 56-81% av bredden. 
Det �r f�ruts�gbart; det finns allts� ingen uppl�sning som �r t.ex.: 10000x50, 300x900 eller 666x111. 
Eftersom de flesta uppl�sningarna �r som en kvadrat som ligger ner p� l�ngsidan, s� kan man f�renkla valideringen av uppl�sningen genom att multiplicera h�jd och bredd f�r den inmatade uppl�sningen, samt de till�tna uppl�sningarna. Sedan j�mf�rs det som vanligt och det n�rmaste till�tna v�rdet v�ljs, eller det h�gsta v�rdet om avst�ndet mellan tv� v�rden �r likadana.