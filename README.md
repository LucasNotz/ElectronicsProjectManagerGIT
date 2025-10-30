# ElectronicsProjectManagerGIT
git init -> allows for git connection

-> defines who is using this git connection
git config --global user.name "insert user" 
git config --global user.email "insert email"

-> origin becomes the repo references, so push origin means push to this repo
git remote add origin https://github.com/your account/your repo.git

->checks
git remote -v

->access token
git remote set-url origin https://username:token@github.com/username/repository.git

->pull everything from repo
git fetch origin

->check branch
git branch --list

-> changes from master
git switch branch name

-> get up to date
git pull origin

->check all actions in repo
git log

->stage modifications
git add .

->make commit
git commit -m "message"

->push to repo (in branch that is being used) <br>
git push repo

