/*
IMPORTANT:
    If using PostgreSQL, update ALL columns defined with 'blob' to 'text',
    as PostgreSQL does not support the 'blob' data type.
*/
CREATE TABLE IF NOT EXISTS oauth2_authorization (
    id varchar(100) NOT NULL,
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorization_grant_type varchar(100) NOT NULL,
    authorized_scopes varchar(1000) DEFAULT NULL,
    attributes blob DEFAULT NULL,
    state varchar(500) DEFAULT NULL,
    authorization_code_value blob DEFAULT NULL,
    authorization_code_issued_at timestamp DEFAULT NULL,
    authorization_code_expires_at timestamp DEFAULT NULL,
    authorization_code_metadata blob DEFAULT NULL,
    access_token_value blob DEFAULT NULL,
    access_token_issued_at timestamp DEFAULT NULL,
    access_token_expires_at timestamp DEFAULT NULL,
    access_token_metadata blob DEFAULT NULL,
    access_token_type varchar(100) DEFAULT NULL,
    access_token_scopes varchar(1000) DEFAULT NULL,
    oidc_id_token_value blob DEFAULT NULL,
    oidc_id_token_issued_at timestamp DEFAULT NULL,
    oidc_id_token_expires_at timestamp DEFAULT NULL,
    oidc_id_token_metadata blob DEFAULT NULL,
    refresh_token_value blob DEFAULT NULL,
    refresh_token_issued_at timestamp DEFAULT NULL,
    refresh_token_expires_at timestamp DEFAULT NULL,
    refresh_token_metadata blob DEFAULT NULL,
    user_code_value blob DEFAULT NULL,
    user_code_issued_at timestamp DEFAULT NULL,
    user_code_expires_at timestamp DEFAULT NULL,
    user_code_metadata blob DEFAULT NULL,
    device_code_value blob DEFAULT NULL,
    device_code_issued_at timestamp DEFAULT NULL,
    device_code_expires_at timestamp DEFAULT NULL,
    device_code_metadata blob DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS oauth2_authorization_consent (
    registered_client_id varchar(100) NOT NULL,
    principal_name varchar(200) NOT NULL,
    authorities varchar(1000) NOT NULL,
    PRIMARY KEY (registered_client_id, principal_name)
);

CREATE TABLE IF NOT EXISTS oauth2_registered_client (
    id varchar(100) NOT NULL,
    client_id varchar(100) NOT NULL,
    client_id_issued_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    client_secret varchar(200) DEFAULT NULL,
    client_secret_expires_at timestamp DEFAULT NULL,
    client_name varchar(200) NOT NULL,
    client_authentication_methods varchar(1000) NOT NULL,
    authorization_grant_types varchar(1000) NOT NULL,
    redirect_uris varchar(1000) DEFAULT NULL,
    post_logout_redirect_uris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    client_settings varchar(2000) NOT NULL,
    token_settings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);

--DROP TABLE t_user;
CREATE TABLE IF NOT EXISTS t_user (
    id bigint auto_increment NOT NULL,
    username varchar(100) UNIQUE NOT NULL,
    password varchar(200) NOT NULL,
    create_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    version int DEFAULT 0 NOT NULL,
    status tinyint DEFAULT 0 NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS t_role (
    id bigint auto_increment NOT NULL,
    name varchar(100) UNIQUE NOT NULL,
    parent_id bigint NOT NULL,
    create_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    version int DEFAULT 0 NOT NULL,
    status tinyint DEFAULT 0 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS t_user_role (
    id bigint auto_increment NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    create_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    update_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    version int DEFAULT 0 NOT NULL,
    status tinyint DEFAULT 0 NOT NULL,
    UNIQUE(user_id, role_id),
    PRIMARY KEY (id)
);


