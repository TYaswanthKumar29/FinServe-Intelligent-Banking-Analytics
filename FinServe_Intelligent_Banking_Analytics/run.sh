#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
mkdir -p out
find src/main/java -name '*.java' -print0 | xargs -0 javac --release 21 -encoding UTF-8 -d out
java -cp out com.finserve.app.FinServeApplication
