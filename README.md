# Pancake



## Installing & Deploying

1. Navigate to your desired installation folder. Example for an installation in the home directory:
```bash
mkdir ~/apps
cd ~/apps
```

2. Clone the repository into the desired folder.
```bash
# Using SSH (recommended, but requires an SSH key):
git clone git@github.com:TASSIA710/Pancake.git

# Using GitHub CLI (also recommended, but requires GitHub CLI to be installed):
gh repo clone TASSIA710/Pancake

# Without SSH (not recommended but easier):
git clone https://github.com/TASSIA710/Pancake.git
```

3. Follow install instructions (see [INSTALLING.md](https://github.com/TASSIA710/Pancake/blob/docs/INSTALLING.md)).

4. Navigate to the installation folder, make the start script executable and and run it:
```bash
# Navigate to the installation folder
cd Pancake

# Make the start script executable
chmod +x run.sh

# Start
./run.sh
```
