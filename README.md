## Benötigte Tools

- Java 17
- Docker (https://www.docker.com/products/docker-desktop/)
- Helm (https://helm.sh/)
    - Zum Ausführen der remote Deployments
- Lens (https://k8slens.dev/)
    - Zum Einsehen der remote deployten Ressourcen
- Git Bash (https://git-scm.com/downloads)
    - Zum Ausführen von Bash Skripten unter Windows
- gcloud CLI (https://cloud.google.com/sdk/docs/install)
    - Für den Zugriff auf das remote Cluster
- docker-credential-gcr (https://github.com/GoogleCloudPlatform/docker-credential-gcr/releases)
    - Für die Authentifizierung an der remote Docker Registry

## Einrichtung

### Einmalig pro Team ausführen

1. Einen Teamnamen `TEAM_NAME` festlegen und eine E-Mail mit Teamname und den Namen und GMail Adressen aller
   Teammitglieder (ggf.
   bitte eine für den Hackathon anlegen, diese wird für die Berechtigungen im Cluster benötigt) an kehrbusch@itestra.de
   senden
2. Das Java Template für euer Team auschecken
3. Kurz warten, bis wir euch das `CLIENT_SECRET` für euer Team mitteilen
4. `set_team_name.sh TEAM_NAME CLIENT_SECRET`
   ausführen, um das Projekt-Template für euer Team zu personalisieren.
5. Das personalisierte Template in eurem Team Git Repository einchecken

### Pro Team-Mitglied ausführen

#### Docker für den Zugriff auf die remote Registry konfigurieren

2. `docker-credential-gcr configure-docker europe-west3-docker.pkg.dev` ausführen, um Docker für den Zugriff auf die
   remote Docker Registry zu konfigurieren
3. Prüfen, ob in `/home/USERNAME/.docker/config.json` in den `credHelpers` die
   Zeile `"europe-west3-docker.pkg.dev": "gcr"` vorhanden ist, ansonsten hinzufügen
4. `docker-credential-gcr gcr-login` ausführen, um dich mit der vorher eingereichten GMail Adresse an der Docker
   Registry deines Teams zu authentifizieren

#### Für remote Cluster authentifizieren

1. `gcloud auth activate-service-account team-[TEAM_NAME]@creators-contest-2022.iam.gserviceaccount.com --key-file=[Pfad zur Key Datei] --project=creators-contest-2022`
2. `gcloud container clusters get-credentials cluster-creators-contest-2022 --region europe-west3`

## Skripte

Zum Ausführen der folgenden Skripte kannst Du unter Windows die Git Bash verwenden. Um die Skripte ausführbar zu machen,
pro Skript (`build_local.sh`, `build_remote.sh` und `deploy.sh`) einmal `chmod +x SCRIPT_NAME` ausführen.

`./build_local.sh`: Baut eure Lösung (Maven Artefakt und Docker Image)

`./build_remote.sh`: Baut eure Lösung (Maven Artefakt und Docker Image) und lädt das Docker Image in die remote Docker
Registry deines Teams

`./deploy.sh`: Deployt das zuvor gebaute Backend-Artefakt in das remote Cluster

## Nützliche Links

Frontend: https://creators-contest-2022.itestra.de/dashboard
Gitlab: https://gitlab.com/creators-contest-2022/team-[CLIENT_ID]
