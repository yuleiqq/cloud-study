

-- OAuth 客户端信息
-- 主要操作 oauth_client_details 表的类是 JdbcClientDetailService
create table oauth_client_details (
                                      client_id VARCHAR(256) PRIMARY KEY,
                                      resource_ids VARCHAR(256),
                                      client_secret VARCHAR(256),
                                      scope VARCHAR(256),
                                      authorized_grant_types VARCHAR(256),
                                      web_server_redirect_uri VARCHAR(256),
                                      authorities VARCHAR(256),
                                      access_token_validity INTEGER,
                                      refresh_token_validity INTEGER,
                                      additional_information VARCHAR(4096),
                                      autoapprove VARCHAR(256)
);

-- 存储从服务器获取的token 数据
-- 主要操作 oauth_client_token 表的类是 JdbcClientTokenServices
create table oauth_client_token (
                                    token_id VARCHAR(256),
                                    token varchar(500),
                                    authentication_id VARCHAR(256) PRIMARY KEY,
                                    user_name VARCHAR(256),
                                    client_id VARCHAR(256)
);



-- 存储access_token
-- 主要操作 oauth_access_token 表的类是 JdbcTokenStore
create table oauth_access_token (
                                    token_id VARCHAR(256),
                                    token varchar(500),
                                    authentication_id VARCHAR(256) PRIMARY KEY,
                                    user_name VARCHAR(256),
                                    client_id VARCHAR(256),
                                    authentication varchar(2000),
                                    refresh_token VARCHAR(256)
);


-- 存储 refresh_token
--  主要操作oauth_refresh_token 表的类是JdbcTokenStore
create table oauth_refresh_token (
                                     token_id VARCHAR(256),
                                     token varchar(2000),
                                     authentication varchar(2000),
);

-- 主要操作oauth_code表的类是 JdbcAuthorizationCodeServices
create table oauth_code (
                            code VARCHAR(256), authentication varchar(2000)
);


-- 授权记录表
create table oauth_approvals (
                                 userId VARCHAR(256),
                                 clientId VARCHAR(256),
                                 scope VARCHAR(256),
                                 status VARCHAR(10),
                                 expiresAt TIMESTAMP,
                                 lastModifiedAt TIMESTAMP
);


-- 用于定制 oauth_client_details 表
create table ClientDetails (
                               appId VARCHAR(256) PRIMARY KEY,
                               resourceIds VARCHAR(256),
                               appSecret VARCHAR(256),
                               scope VARCHAR(256),
                               grantTypes VARCHAR(256),
                               redirectUrl VARCHAR(256),
                               authorities VARCHAR(256),
                               access_token_validity INTEGER,
                               refresh_token_validity INTEGER,
                               additionalInformation VARCHAR(4096),
                               autoApproveScopes VARCHAR(256)
);