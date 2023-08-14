INSERT IGNORE INTO PUBLIC.OAUTH2_REGISTERED_CLIENT
(ID, CLIENT_ID, CLIENT_ID_ISSUED_AT, CLIENT_SECRET, CLIENT_SECRET_EXPIRES_AT, CLIENT_NAME, CLIENT_AUTHENTICATION_METHODS, AUTHORIZATION_GRANT_TYPES, REDIRECT_URIS, POST_LOGOUT_REDIRECT_URIS, SCOPES, CLIENT_SETTINGS, TOKEN_SETTINGS)
VALUES('b025ef2c-60d6-42e2-a008-9bcf54f33ad5', 'auth2-client', '2023-08-10 16:42:12.681', '$2a$10$pk1QAreSzps1wEiAjSPH/upkHTtFsEmI4G48u5TwltH/omvqVB5kW', NULL, 'b025ef2c-60d6-42e2-a008-9bcf54f33ad5', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code', 'http://127.0.0.1:8080/authorized,http://127.0.0.1:8080/login/oauth2/code/auth2-client-oidc,https://forsrc-auth2-client.duckdns.org/authorized,https://forsrc-auth2-client.duckdns.org/login/oauth2/code/auth2-client-oidc', 'http://127.0.0.1:8080,https://forsrc-auth2-client.duckdns.org', 'openid,profile,message.read,message.write', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');


INSERT IGNORE INTO PUBLIC.T_USER
(ID, USERNAME, PASSWORD, CREATE_AT, UPDATE_AT, VERSION, STATUS)
VALUES(1, 'forsrc', '$2a$10$qhHgDXJlKh5kiXOiz1E0jeq50z2oWqRP5QZEvC8M9rQRgvb7wZDqe', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0);

INSERT IGNORE INTO PUBLIC.T_ROLE
(ID, NAME, PARENT_ID, CREATE_AT, UPDATE_AT, VERSION, STATUS)
VALUES(1, 'ROLE_ADMIN', 0, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0);

INSERT IGNORE INTO PUBLIC.T_USER_ROLE
(ID, USER_ID, ROLE_ID, CREATE_AT, UPDATE_AT, VERSION, STATUS)
VALUES(1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0);
