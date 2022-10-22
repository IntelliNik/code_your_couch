#!/bin/bash
# usage: set_team_name.sh TEAM_NAME CLIENT_SECRET

TEAM_NAME="$1"
CLIENT_SECRET="$2"

sed -i "s/TEAM_NAME/$TEAM_NAME/g" ./pom.xml
sed -i "s/TEAM_NAME/$TEAM_NAME/g" ./deploy.sh
sed -i "s/CLIENT_ID/$TEAM_NAME/g" ./deployment/values.gcloud.yaml
sed -i "s/TEAM_NAME/$TEAM_NAME/g" ./deployment/values.gcloud.yaml
sed -i "s/CLIENT_SECRET/$CLIENT_SECRET/g" ./deployment/values.gcloud.yaml
