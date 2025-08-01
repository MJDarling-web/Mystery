Tables:
story
    id (PK)
    title 
    description
    Starting_setting 
    created_by_user_id
    created_at

Scene (session)
    id (PK)
    story_id (FK to story.id)
    admin_user_id
    current_clue_index (or pointer to progression)
    started_at
    is_active (boolean)

character
    id (PK)
    session_id (FK to session.id)
    name
    description
    assigned_user_id (nullable, FK to a user table)
    custom_notes

clue
    id (PK)
    story_id (FK to story.id)
    content
    order? (int)

user (optional if you want account tracking)
    id (PK)
    email
    username
    role (admin/player)

