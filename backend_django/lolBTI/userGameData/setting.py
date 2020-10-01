api_key_list = [
    'RGAPI-d3a53c22-f940-40b8-9e4c-089ad160d8f2',
    'RGAPI-2866c236-5976-4cac-8955-49fccb487e8b',
    'RGAPI-1389cd81-c02e-4fa1-9654-80579633a8ff',
    'RGAPI-24091344-7123-4eac-8ebc-9f9c6e75311a',
    'RGAPI-754d8513-daaf-4192-831f-a6953405e338',
    'RGAPI-774ab6ec-eccf-4e33-8fce-27065d9e2a4c',
    'RGAPI-dc94fdf0-94aa-4697-bf21-092ded3f9f18',
    'RGAPI-914c67f1-e069-431b-833f-4262481851d2'
]

sohwan_mastery = None

updated = False

def update_sohwan_mastery():
    global updated
    print("sohwan_mastery updated")
    updated = True

def apply_sohwan_mastery():
    global updated
    print("sohwan_mastery applied")
    updated = False