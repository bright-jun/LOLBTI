api_key_list = [
    'RGAPI-45f94ce0-9ff2-4d7a-9688-b5b6b0ded896',
    'RGAPI-63bb2763-e169-4cb3-b337-4d015506ea15',
    'RGAPI-de41fea5-6ade-4726-813c-0257444aa1f9'
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