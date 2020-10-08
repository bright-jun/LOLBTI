api_key_list = [
'RGAPI-a6887eee-3a6b-44cb-b087-6aa393c2e8c5',
'RGAPI-d81f6f17-5313-492f-a5b0-a0f58432b53f',
'RGAPI-c59d1297-b70b-4037-ab56-a32b1ccdafa0',
'RGAPI-02c51488-08b5-4e01-9864-c5e9706c0530',
'RGAPI-37ede65e-45e6-482c-81ad-4ca0c4d11687',
'RGAPI-68757624-139c-47f7-96dc-a08cdb6b0ede',
'RGAPI-b3aae498-5ce4-48bc-ab55-9bfaab3b2cfd',
'RGAPI-d2179c86-dedf-4624-978a-e05276712147',
'RGAPI-2923d7ea-6a2d-4954-99e4-a0ff7542def9',
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