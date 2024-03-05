#!/usr/bin/env bash
set -ex
git fetch origin

offset=${1:0} # first parameter with default value 0
year=$(date +%y)
week=$(date +%V)
week=$((10#${week} + offset))

if [ $((week % 2)) -eq 0 ]; then #if even week then Week + 1
  week=$((10#${week} + 1))
fi

if [[ 10#${week} -gt 52 ]]; then
  week=1
  year=$((10#${year} + 1))
fi

latest_tag=$(git tag -l --sort=-version:refname "v*.*.*" | sed -n "1p")
IFS='.' read -r -a vers <<<"$latest_tag" # split by dot

major=${vers[0]}
current_week=${vers[1]:(-2)}
current_increment=${vers[2]%"-rollback"}
current_increment=${current_increment%"-beta"}

increment=0
formattedWeek="$(printf "%02d" $week)"

if [ "$current_week" == "$formattedWeek" ]; then
  increment=$((10#${current_increment} + 1))
fi

new_version="${major}.${year}${formattedWeek}.${increment}"

commit_count=$(git rev-list --count origin/develop..HEAD)
new_version="${new_version}-beta.${commit_count}"
echo "New version number is: $new_version"

git tag -a "$new_version" -m "$new_version"
git push origin "$new_version"
