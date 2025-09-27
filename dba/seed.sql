-- seed.sql
BEGIN;

-- Users
INSERT INTO users (id, is_admin, username, password, email) VALUES
                                                                (1, true,  'host', 'devpass', 'host@example.com'),
                                                                (2, false, 'mira', 'mira123', 'mira@example.com'),
                                                                (3, false, 'zara', 'zara123', 'zara@example.com')
    ON CONFLICT (id) DO NOTHING;

-- Stories (owned by host id=1)
INSERT INTO story (id, title, description, setting, creator_id) VALUES
                                                                    (101, 'Haunted Harbor',        'A seaside mystery in heavy fog.',                   'Pier 9',             1),
                                                                    (102, 'Midnight Masquerade',   'Masks, music, and a missing heirloom.',            'Grand Ballroom',     1),
                                                                    (103, 'Library Whodunit',      'A rare book vanishes during a storm.',             'City Library',       1)
    ON CONFLICT (id) DO NOTHING;

-- Characters
INSERT INTO character (id, story_id, picture_url, name, bio, role, is_guilty) VALUES
                                                                                  (1001, 101, NULL, 'Captain Grey',     'Gruff dockmaster who knows every ship.',           'Dockmaster', false),
                                                                                  (1002, 101, NULL, 'Mira Vale',        'Journalist chasing a smuggling lead.',             'Journalist', false),
                                                                                  (2001, 102, NULL, 'Zara Noir',        'Mysterious guest in a silver mask.',               'Guest',      true),
                                                                                  (2002, 102, NULL, 'Maître D’ Laurent','Sees everything, says little.',                    'Maître D’',  false),
                                                                                  (3001, 103, NULL, 'Professor Wren',   'Curator of rare manuscripts.',                     'Curator',    false),
                                                                                  (3002, 103, NULL, 'Ari Patel',        'Night shift librarian and coder.',                 'Librarian',  false)
    ON CONFLICT (id) DO NOTHING;

-- Scenes
INSERT INTO scene (id, story_id, title, description, image_url, display_order, is_active) VALUES
                                                                                              (1101, 101, 'Fog on Pier 9',      'Footprints end at the waterline.',               NULL, 1, true),
                                                                                              (1102, 101, 'The Hidden Ledger',  'A torn page lists ship codes.',                  NULL, 2, false),
                                                                                              (2101, 102, 'Unmasking',          'Guests swap masks at midnight.',                 NULL, 1, true),
                                                                                              (2102, 102, 'Shattered Display',  'Glass case broken, jewel gone.',                 NULL, 2, false),
                                                                                              (3101, 103, 'Power Outage',       'Lights cut as thunder rolls.',                   NULL, 1, true),
                                                                                              (3102, 103, 'Locked Reading Room','Key missing from the hook.',                     NULL, 2, false)
    ON CONFLICT (id) DO NOTHING;

-- Clues
INSERT INTO clue (id, story_id, image_url, title, description, location_found, is_found) VALUES
                                                                                             (1201, 101, NULL, 'Ledger Scrap',    'Salt-stained page with partial ship code.', 'Dock bollard', true),
                                                                                             (1202, 101, NULL, 'Oilcloth Fiber',  'Scrap snagged on a barnacle.',              'Pilings',      false),
                                                                                             (2201, 102, NULL, 'Silver Feather',  'Feather from a silver mask near balcony.',  'Balcony',      true),
                                                                                             (2202, 102, NULL, 'Glitter Print',   'Footprint in glitter leading backstage.',   'Stage left',   false),
                                                                                             (3201, 103, NULL, 'Ozone Smell',     'Ozone near breaker box after outage.',      'Basement',     true),
                                                                                             (3202, 103, NULL, 'Missing Key',     'Dust-free outline where key used to hang.', 'Reading room', false)
    ON CONFLICT (id) DO NOTHING;

-- Keep SERIAL sequences in sync with the max(id)
SELECT setval(pg_get_serial_sequence('users','id'),      COALESCE((SELECT MAX(id) FROM users),0),      true);
SELECT setval(pg_get_serial_sequence('story','id'),      COALESCE((SELECT MAX(id) FROM story),0),      true);
SELECT setval(pg_get_serial_sequence('character','id'),  COALESCE((SELECT MAX(id) FROM character),0),  true);
SELECT setval(pg_get_serial_sequence('scene','id'),      COALESCE((SELECT MAX(id) FROM scene),0),      true);
SELECT setval(pg_get_serial_sequence('clue','id'),       COALESCE((SELECT MAX(id) FROM clue),0),       true);

COMMIT;
