# Agenda
- Et lite eksempel med testdata
- Loganalyse 
- Endring av kode med [aider](https://aider.chat/)

# om modellene 
Du kan både bruke både GPT-modeller og Anthropic-modeller i dette forsøket (aider kan bruke begge). Uansett må du ha en konto, API-ey og muligens også 


# Anthropic
Jeg liker å bruke Anthropic. De leverer modeller som Claude, Sonnet osv. Også har de en bra workbench:  [Consolle](https://console.anthropic.com/workbench/) 

## testdata-case 

Bruker Antropic til å generere testdata. 

Machine prompt:
    I will give you an id of a kindergarden and a comma-separayed list of departments, like this: 

    [kidergarden]: [department], [department], [...],  [department]

    You will produce insertstatements that can be inserted in a table in in a postgreSQL database 

    CREATE TABLE dibber_mobile_app_local.daily_dept_activity_summary (
       dept_activity_id character varying(36) NOT NULL,
       activity_date date,
       activity_status character varying(255),
       create_date_time timestamp without time zone,
       created_by character varying(255),
       department_id character varying(255),
       edited_summary_text character varying(3000),
       is_deleted boolean NOT NULL,
       kindergarten_id character varying(255),
       modified_by character varying(255),
       modified_date_time timestamp without time zone,
       start_edited_date_time timestamp without time zone,
       summary_text character varying(3000)
    );

Userprompt: 

`Please produce a realistic summary for the following kindergarden and departments 12: 1,2,3,4,5,6,7`

Man kan selvsagt bruke tilsvarende til å lage API-kall med curl. Eller rett og slett genererer Junit-tester. Det som er artig er at den lager "realistiske" testdata uten spesielt mye informasjon om konteksten. 

## Loganalyse
Pre-req: Jeg bruker pyton virtual environment

    python3 -m venv path/to/venv
    source path/to/venv/bin/activate 
    python3 -m pip install anthropic

For å bruke anthropic fra kommandolinje må du ha en API-key, som du henter ut fra [Consolle](https://console.anthropic.com/workbench/) 

### Usecase 
For en kunde lurte vi på  hvor mye av tiden som ble brukt til maintenance, feilretting og nye features. Tenkte at commit-loggen kunne gi en indikasjon:

For å henter ut commitloggen for et prosjekt på et greit format for videre prossesering kan man f.eks skrive.  

   `git log --pretty=format:'%h : %s : %ae`


Kjør antrhopic consolle med følgende eksempel: 
Systemprompt: 

    Jeg sender deg en commit-melding på følgende format: 

    [sha] : [commitmelding] : [utvikler]

    Du skal kategorisere commitmeldingen som vedlikehold, bugfiks eller nyutvikling, og returnere en json-struktur som inneholder commitmeldingen, inklusive kategoriseringen. Returstrukturen må være gyldig json, og bare det. 

Userprompt: 

`8e2f0c5d : Endrer litt på grensesnittet for informasjonskrav slik at disse kan avgjøre om de er relevante for den gitte behandling : Tobias Torrissen`

Vis kode og forklar.  Kopier ut i en Pythonfil og kjør den i consolle. 

Kjør ut alle logentries til en fil: 

    `git log --pretty=format:'%h : %s : %ae' > commitlog.txt

Ta opp anthropic consolle slik at du kan endre koden fra sist program: 

    Jeg vil at du skal endre følgende python program til å lese alle meldingene fra en fil som heter commitlog.txt. Det er en melding pr linje og jeg vil ha skrevet alle kategoriserte meldinger til en fil som en json-array. Gjør ett kall til anthropic for hver melding. 

    Jeg vil også at programmet summerer opp antallet i hver kategori og skriver ut som en rapport til slutt. Gjør denne som en tabell med tydlige overskrifter

    import anthropic
    
    client = anthropic.Anthropic(
        # defaults to os.environ.get("ANTHROPIC_API_KEY")
        api_key=[API-key som du finner i anthropic consolle],
    )
    
    message = client.messages.create(
        model="claude-3-5-sonnet-20240620",
        max_tokens=1000,
        temperature=0,
        system="Jeg sender deg en commit-melding på følgende format: \n\n    [sha] : [commitmelding] : [utvikler]\n\n    Du skal kategorisere commitmeldingen som vedlikehold, bugfiks eller nyutvikling, og returnere en json-struktur som inneholder commitmeldingen, inklusive kategoriseringen: 
        {message: [message], category:[category]}
        Returstrukturen må være gyldig json, og bare det. ",
        messages=[
            {
                "role": "user",
                "content": [
                    {
                        "type": "text",
                        "text": " 123 : fikser en feil: tobias torrissen"
                    }
                ]
            }
        ]
    )
    print(message.content)


Koden kan så tas ut i en pythonfil i samme directory som log-eksporten og kjøres. Det er lurt å ta bort mange entries, ellers tar det tid. 
    
# Aider 
Fordelen med denne vs "vanlige" kodegeneratorer er at den kan jobbe på flere filer samtidig. Dermed funker den nesten som som en "parprogrammeringsassistent"

Før du fyrer opp aider må du huske på å å sette anthropic key:
`export ANTHROPIC_API_KEY=[API-key som du finner i anthropic consolle]`
`export OPENAI_API_KEY=[API-key som du finner i OpenAI consolle]`

Aider startes med:
`aider`

For "dry-runs", altså uten gitcommits kan man bruke: 
`aider --no-auto-commits`


mvn clean install 
mvn spring-boot:run

Kjøre opp to vinduer. Ett for Aider og ett for bygging
`curl localhost:8080/motorcycles`
`curl localhost:8080/motorcycles/1`

### Logging 

`/add src/main/java/no/torrissen/motorcycles/MotorcyclesController.java`
`/add pom.xml`

`I want to add logging with log4j2 to all APIs in the `MotorcyclesController` class`

`/run mvn clean install`
`/run mvn spring-boot:run` 
`curl localhost:8080/motorcycles`
`curl localhost:8080/motorcycles/1`

## PROMPT - Legge til persistens
Legge til persistens med JPA

`Add persistence to the app, using postgreSQL and JPA repositories. All model classes should be storable.`

### Lage en database i docker 

`Create a docker compose file that starts a docker container with a postgreSQL database with corresponding properties to those in in application properties`

### Start 
Sjekk docker app om den kommer opp greit. 

`docker-compose up -d`
`docker-compose down --volumes`

### Legge inn motorsykkel
    curl -X POST http://localhost:8080/motorcycles \
    -H "Content-Type: application/json" \
    -d '{
      "model": "Monster",
      "brand": "Ducati",
      "year": 2020
    }'

## PROMPT - UPDATE METHODS
`Update methods getAllMotorcycles and getMotorcycleById to use MotorcycleRepository and return entities from the database`

`curl http://localhost:8080/motorcycles´

    curl -X POST http://localhost:8080/motorcycles \
    -H "Content-Type: application/json" \
    -d '{
      "model": "Panigale V4",
      "brand": "Ducati",
      "year": 2020
    }'

`curl http://localhost:8080/motorcycles´


### If all else fails: 
`git checkout start`
`git checkout logging`
`git checkout JPA `
`git checkout Docker`
`git checkout endpoint` 
`git checkout updateDatabase`
`git checkout master` 



Dockerfile 
### Opprette en lokal database
version: '3.8'
services:
  db:
    image: postgres:13
    container_name: postgres_motorcycles
    environment:
      POSTGRES_DB: motorcycles
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: mysecretpassword
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
