# EncryptionDecryptionProject
Run from command line, read message from file/command line and encrypt/decrypt(in unicode or shift algorithm) into another file/console 

Run by inputting command in terminal: java -cp . EncryptionDecryption -mode dec -key 5 -alg shift -in encryptdecrypt.txt -out endresult.txt -data "Welcome to GitHub!"

//all '-' commands are optional, will default to other options

//-mode: either enc or dec

//-key: any number you want to shift by

//-alg: algorithm shift or unicode

//-in choose input file

//-out choose output file

//-data override input file and get message from data key
