api_key_list = [
'RGAPI-a6887eee-3a6b-44cb-b087-6aa393c2e8c5',
'RGAPI-e1f607f6-6226-4fb3-a4c7-21f57e75116c',
'RGAPI-87179247-3adf-4298-a667-f317181de631',
'RGAPI-ec2cf27a-96fd-4e47-8516-613ebb934fd5',
'RGAPI-c1daa0f2-295f-43f0-b9fb-8c785d1c80c2',
'RGAPI-8cc8c747-bf1a-4230-a47e-2b882296419c',
'RGAPI-944325b9-d302-4493-81aa-10dfebafb2a7',
'RGAPI-7f0a690d-5f0b-4b9a-95fe-a7c92ac88d46',
'RGAPI-12e3ea83-5d84-4861-ae0f-9a10f64bdf32',
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