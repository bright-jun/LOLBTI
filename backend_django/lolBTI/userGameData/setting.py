api_key_list = [
    # 'RGAPI-8a4bff97-1355-49ac-9c84-92c6fbb6b796',
    # 'RGAPI-dad7dc1c-0a54-4026-9283-f1bd120dd7a4',
    # 'RGAPI-bf2b1232-7cb0-4143-b58d-223aa29fe0e5',
    # 'RGAPI-635f48bb-bf12-495b-928c-963f7b6d7c87',
    # 'RGAPI-37dce2dd-2b4a-4a82-bc0c-e045616db918',
    # 'RGAPI-8523df6b-f3a1-472e-a064-038300e113a0',
    # 'RGAPI-c447bc44-934e-4fd5-950c-101f999d065b',
    # 'RGAPI-b6822d6f-04fd-4d98-8f41-705d9d038491'
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