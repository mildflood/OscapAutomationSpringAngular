var data = 
[
    {
        "id": 10,
        "name": "hawk",
        "price": "$40",
        "quality": "excellent"
    },
        
    {
        "id": 20,
        "name": "falcon",
        "price": "$150",
        "quality": "phenomenal"
    },
    {
        "id": 30,
        "name": "osprey",
        "price": "$160",
        "quality": "extra-ordinary"
    },
    
    {
        "id": 40,
        "name": "eagle",
        "price": "$200",
        "quality": "beautiful"
    }
];

$(function(){
    $('#table').bootstrapTable({
        data: data
    });
});