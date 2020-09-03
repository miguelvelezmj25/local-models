#!/usr/bin/env bash

BASE=$(pwd)

cd "$BASE"/../../../../../../../../../../../../matlab/edu/cmu/cs/mvelezce/lc/perf/behavior/plots/configs/ || exit

for d in */ ; do
    cd "$d" || exit
    for dc in */ ; do
      cd "$dc" || exit
      for m in *m; do
        echo "Plotting" "$m"
        rm -rf ./*.pdf
        /Applications/MATLAB_R2019b.app/bin/matlab -nodisplay -nosplash -nodesktop -r "run('./$m'); exit;"
      done
      cd ..
    done
    cd ..
done
