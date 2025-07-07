#!/bin/sh
# Usage: ./wait-for-mysql.sh <host> <port> <command...>
# Save this file at the project root (same directory as your Dockerfile).
# On your local machine, run:
#   chmod +x wait-for-mysql.sh
# This sets the executable bit so Docker COPY preserves it when building the image.

host="$1"
port="$2"
shift 2

echo "Waiting for MySQL at $host:$port..."
while ! nc -z "$host" "$port"; do
  echo "Still waiting for MySQL..."
  sleep 1
done

echo "MySQL is up - executing command"
exec "$@"