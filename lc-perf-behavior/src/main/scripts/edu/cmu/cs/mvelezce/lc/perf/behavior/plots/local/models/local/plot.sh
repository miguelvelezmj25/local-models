#!/usr/bin/env bash

BASE=$(pwd)

cd "$BASE"/../../../../../../../../../../../../matlab/edu/cmu/cs/mvelezce/lc/perf/behavior/plots/local/models/local/ || exit

for d in */; do
  echo "Plotting" "$d"
  cd "$d" || exit
  for dc in */; do
    cd "$dc" || exit
    rm -rf ./*.pdf
    for m in *m; do
      echo "Plotting" "$m"
      /Applications/MATLAB_R2019b.app/bin/matlab -nodisplay -nosplash -nodesktop -r "run('./$m'); exit;"
    done
    cd ..
  done
  cd ..
done
