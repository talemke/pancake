# Pancake

![Issues](https://img.shields.io/github/issues/TASSIA710/Pancake?style=for-the-badge)
![Pull Requests](https://img.shields.io/github/issues-pr/TASSIA710/Pancake?style=for-the-badge)
![License](https://img.shields.io/github/license/TASSIA710/Pancake?style=for-the-badge)

![Java CI](http://img.shields.io/github/workflow/status/TASSIA710/Pancake/Java%20CI?style=for-the-badge&label=Java%201.9)

**This is still a work in progress!**

A deploy-ready webmail server written in Java. Supports:

| Protocol | Supported          |
| -------- | ------------------ |
| SMTP     | :heavy_check_mark: |
| POP3     | :x:                |
| IMAP     | :x:                |



## Installing & Deploying (on Ubuntu 18.04)

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

3. Follow install instructions (see [INSTALLING.md](https://github.com/TASSIA710/Pancake/blob/main/docs/INSTALLING.md)).

4. Follow DNS setup instructions (see [SETUP_DNS.md](https://github.com/TASSIA710/Pancake/blob/main/docs/SETUP_DNS.md)).

5. Navigate to the installation folder, make the start script executable and and run it:
```bash
# Navigate to the installation folder
cd Pancake

# Make the start script executable
chmod +x run.sh

# Start
./run.sh
```

6. You can now access the application at http://your-ip-address:8080
