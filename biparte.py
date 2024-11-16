#map graph to people and jobs
persons = ['Person 0', 'Person 1', 'Person 2', 'Person 3', 'Person 4', 'Person 5']
jobs = ['Job 0', 'Job 1', 'Job 2', 'Job 3', 'Job 4', 'Job 5']

#graph as adjacency list
graph = [
    [1, 3],      
    [0, 2, 3],   
    [1, 4],     
    [],          
    [2],          
    []            
]
assigned = [-1] * len(jobs)

#depth first search
def dfs(i, assigned, seen):

    #for every job each person is connected to 
    for j in graph[i]:
        #if unvisited set to visited so we know not to reassign   
        if seen[j] == False:
            seen[j] = True  
            #if the job job is unassigned set job to person
            #or if job is assigned check call dfs to see if the person can be reassigned
            if assigned[j] == -1 or dfs(assigned[j], assigned, seen):
                #assign job to person (i)
                assigned[j] = i  
                return True
                
    return False

def max_match():
    #store number of matches
    result = 0
    #for all persons in graph
    for i in range(len(persons)):
        #all jobs = not seen 
        seen = [False] * len(jobs)
        #call dfs for each person
        if dfs(i, assigned, seen):
            result += 1

    return result

print(f"Maximum number of applicants that can get a job is {max_match()}\n")

for job, person in enumerate(assigned):
    if person != -1:
        print(f"{persons[person]} is assigned to {jobs[job]}")
    else:
        print(f"{jobs[job]} is not assigned to anyone")