CREATE OR REPLACE VIEW sipfriends AS
SELECT
    id,
    name,
    secret,
    context,
    host,
    ip_addr AS ipaddr,
    sip_type AS type,
    extension_id
FROM sip_friend;
