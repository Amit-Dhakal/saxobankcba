# 🏦 Saxo Bank API Integration – Certificate-Based Authentication

This project integrates with Saxo Bank's OpenAPI using **mutual TLS (mTLS)** authentication via client certificates.
It allows secure access to Saxo's financial services by validating both client and server identities using digital certificates.

---

## 🚀 Features

- ✅ Certificate-based (mTLS) authentication
- ✅ Encrypted communication via HTTPS
- ✅ Integration with Saxo's OpenAPI for trading & portfolio access
- ✅ Secure token acquisition if needed (OAuth fallback)
- ✅ Customizable via configuration properties

---

## 🔐 Authentication Method: JWT Bearer Token (RFC 7523)

This project uses the OAuth 2.0 JWT Bearer Token flow to obtain an access token. A signed JWT is created and sent as an assertion to the authorization server. 
The server validates the JWT and issues an access token, which is then used to access protected APIs.

- **Grant Type:** `urn:ietf:params:oauth:grant-type:jwt-bearer`
- **Assertion:** Signed JWT using the private key
- **Token Endpoint:** `/token`

## ⚙️ Configuration

> Configure these in your `application.properties` or environment

### 🔧 Required Properties:

| Property                 | Description                      |
|--------------------------|----------------------------------|
| `saxo.clientId`          | Your Saxo API client ID          |
| `saxo.clientSecret`      | Your Saxo API client secret      |
| `saxo.authUri`           | OAuth Authorization URL          |
| `saxo.tokenUri`          | Token Exchange Endpoint          |
| `saxo.redirectUri`       | Your app's OAuth callback URL    |
| `saxo.resourceApiUrl`    | Base URL for accessing APIs      |

Example for `application.properties`:

properties
saxo.clientId="YOUR CLIENT ID HERE"
saxo.clientSecret="YOUR CLIENT SECRET"
saxo.authUri=https://sim.logonvalidation.net/authorize
saxo.tokenUri=https://sim.logonvalidation.net/token
saxo.redirectUri=http://localhost:8080/oauth/callback
saxo.resourceApiUrl=https://gateway.saxobank.com/sim/openapi
