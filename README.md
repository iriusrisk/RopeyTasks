# Overview
ropeytasks is a simple web application that is deliberately built with a number of included security vulnerabilities.  These include:

- Blind HQL injection
- XSS
- CSRF
- Case insensitive passwords
- No SSL
- Lack of HttpOnly and secure flags on session cookies

# Running
## 1. With grails
The recommended way to run this is to install www.grails.org version >= 2.0.3 so that you can see and modify the code.

## 2. Self-executing jar
The .jar is executable with: java -jar ropeytasks.jar

## 3. Deploy on servlet container
Drop the .war on a servlet container

# Credentials
- admin/password
- bob/password
- alice/password
