#!/usr/bin/env bash
# Starts a standalone H2 TCP server for custom datasource exercises.
# Data is stored in ./h2-data/ (persists between restarts).
#
# Connect from your app with:
#   spring.datasource.url=jdbc:h2:tcp://localhost:9092/./pizzadb
#   spring.datasource.driver-class-name=org.h2.Driver
#   spring.datasource.username=sa
#   spring.datasource.password=
#
# H2 web console: http://localhost:8082
#   JDBC URL to paste there: jdbc:h2:tcp://localhost:9092/./pizzadb

H2_JAR="$HOME/.m2/repository/com/h2database/h2/2.3.232/h2-2.3.232.jar"

if [ ! -f "$H2_JAR" ]; then
  echo "H2 jar not found at $H2_JAR"
  echo "Run './mvnw dependency:resolve' first to populate the local Maven cache."
  exit 1
fi

DATA_DIR="$(cd "$(dirname "$0")" && pwd)/h2-data"
mkdir -p "$DATA_DIR"

echo "Starting H2 TCP server..."
echo "  TCP:  jdbc:h2:tcp://localhost:9092/./pizzadb"
echo "  Web console: http://localhost:8082"
echo "  Data directory: $DATA_DIR"
echo ""
echo "Press Ctrl+C to stop."
echo ""

java -cp "$H2_JAR" org.h2.tools.Server \
  -tcp -tcpPort 9092 -tcpAllowOthers \
  -web -webPort 8082 \
  -baseDir "$DATA_DIR" \
  -ifNotExists
