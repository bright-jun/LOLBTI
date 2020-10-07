api_key_list = [
    'RGAPI-a6887eee-3a6b-44cb-b087-6aa393c2e8c5',
    # 'RGAPI-fe0fd301-b79e-4ece-9848-4d5d4d5ffbec',
    # 'RGAPI-09b6f585-426c-4edd-9ba3-9d7dae8700f0',
    # 'RGAPI-1050008e-d0f1-4ec0-acb4-15367bd8d372',
    # 'RGAPI-f7b837d5-dac2-480f-bcf5-e94e7d88d0bb',
    # 'RGAPI-a6342848-706a-487e-b7af-ac0754e00189',
    # 'RGAPI-ac79014f-ca1f-408e-a01e-9c1847825836',
    # 'RGAPI-ea840e63-994d-4ba6-9df5-a62bf9900c1a',
    # 'RGAPI-74b68ab8-9687-4049-af0d-a4ed558ef891',
    # 'RGAPI-e275de9f-9600-49b2-a943-a3dcc2a72e5e',
    
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