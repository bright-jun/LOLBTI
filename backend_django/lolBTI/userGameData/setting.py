api_key_list = [
    'RGAPI-b6822d6f-04fd-4d98-8f41-705d9d038491',
    'RGAPI-1a4b3463-b171-414a-b26d-3c0eb1bc6a66',
    'RGAPI-6e9d1947-08ff-45df-889d-ed4cba46849f'
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