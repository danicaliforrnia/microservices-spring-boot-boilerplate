INSERT INTO rol_role (rol_name, rol_code) VALUES ('administrator', 'ROLE_ADMIN')
INSERT INTO rol_role (rol_name, rol_code) VALUES ('user', 'ROLE_USER')

INSERT INTO per_permission (per_code, per_description) VALUES ('GET_ROL', 'get roles')
INSERT INTO per_permission (per_code, per_description) VALUES ('GET_USU', 'get users')
INSERT INTO per_permission (per_code, per_description) VALUES ('CRE_USU', 'create users')
INSERT INTO per_permission (per_code, per_description) VALUES ('UPD_USU', 'update users')
INSERT INTO per_permission (per_code, per_description) VALUES ('DEL_USU', 'delete users')

INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(1, 1)
INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(1, 2)
INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(1, 3)
INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(1, 4)
INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(1, 5)
INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(2, 1)
INSERT INTO rtp_role_to_permission (rol_id, per_id) VALUES(2, 2)

INSERT INTO usr_user (usr_username, usr_full_name, usr_password, usr_is_active, usr_password_active, usr_account_locked, rol_id, usr_is_signed_in, usr_sign_in_at) VALUES ('admin', 'Administrador', '$2a$10$zem6HtkxbJz3lnEdST8Yte9Xd11iFDQyBdB6YdrYt/bjPiouZRZr6', true, true, false, 1, 0, NOW())
INSERT INTO usr_user (usr_username, usr_full_name, usr_password, usr_is_active, rol_id, usr_is_signed_in, usr_sign_in_at) VALUES ('johnDoe', 'John Doe', '$2a$10$zem6HtkxbJz3lnEdST8Yte9Xd11iFDQyBdB6YdrYt/bjPiouZRZr6', true, true, false, 2, 0, NOW())
