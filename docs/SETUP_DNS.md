# Setting up your DNS

This is a guide on how you need to setup your DNS for Pancake to work properly.
I will use `example.com` as the domain name and `192.168.31.15` as the IPv4 address.
Replace them with your own.

**Tip:** If you need to specify the root of your domain (`example.com`) and not a subdomain (`subdomain.example.com`),
most DNS providers require you to enter `@` instead of your domain.



### A Record

First, you will need to create an A record that points to your SMTP server (Pancake).
| Type | Name             | IPv4 address  | TTL  |
| ---- | ---------------- | ------------- | ---- |
| A    | smtp.example.com | 192.168.31.15 | Auto |

**Note:** If you're using a proxy that denys SMTP traffic on port 25 (e.g. CloudFlare), you need to make sure
that traffic on this record isn't proxied.

**Note 2:** I'm using a TTL (Time-To-Live) of 'Auto' in this example, but pretty much any common-sensely chosen value (e.g. 1h, 3h) will work aswell.



### MX Record

No we need to create an MX record. This record specifies to which SMTP server emails sent to your domain are sent to.
You need to set the mail server to the value you used above when setting up the A record.
| Type | Name        | Mail Server      | TTL  | Priority |
| ---- | ----------- | ---------------- | ---- | -------- |
| MX   | example.com | smtp.example.com | Auto | 10       |

**Note:** The name of `example.com` here specifies how to handle mails for e.g. `someUser@example.com`.
If you wish to handle mails for `someUser@mail.example.com` you would need to set the name to `mail.example.com`.

**Note 2:** See the note above for more information on how to set the TTL.
