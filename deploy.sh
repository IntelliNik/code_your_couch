#!/bin/bash
# usage: deploy.sh
date=$(date +%s)
helm upgrade --install --namespace team-TEAM_NAME --values "deployment/values.gcloud.yaml" --set metadata.date="$date" solution deployment/chart
