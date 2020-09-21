#!/usr/bin/env bash

NULL="null"
PROGRAMS="berkeleyDb lucene convert runBenchC"
BASE=$(pwd)

cd "$BASE"/../../../../../../../../../../../../ || exit
pwd

program=$NULL

run() {
  local program=$1

  echo ""
  echo "Running" "$program"
  echo ""

  mvn test -Dtest=edu.cmu.cs.mvelezce.lc.execute.jprofiler.snapshot.IDTAJProfilerSamplingExecutorTest#"$program"

  echo ""
  echo "Done with" "$program"
  echo ""
  sleep 60s

  cd .. || exit
}

for entry in "$@"; do
  if [[ $PROGRAMS =~ (^|[[:space:]])$entry($|[[:space:]]) ]]; then
    program=$entry
  else
    echo "Could not find program" "$entry"
    continue
  fi

  run "$program"

done
