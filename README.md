# leadiqassignment

We are linking a transaction to maximum one parent transaction. That means we will have a collection of trees where the nodes are connected by parent-child relation.

An Alternate Approach:
If we store the parent->child relations too instead of just child->parent.
Then the sum problem is just calling the sum function recursively on the children and add their sums + current node value.
Type api is also quite similar, here we just pick each tree in the collection one by one & start parsing the tree from the head. whenever we find the transaction with required type we add it to the list & in the end return the list.

This approach both sum & type api are O(N) where add is O(1). (opposite of the current implementation where time is optimized


Scaling:
We can see that for sum & add problem, the nodes in a particular tree are independent of other trees.
lets say we identify the tree by the head of the tree, we can store the head's Transaction id of the tree in the transaction along with parent_id.
If we shard the nodes based on the head node then, all the transactions of the tree will be on the same machine which will result in fast query times for sum & add. In this case listing all transactions of a particular type will be slow as it has to query all the systems to get the result.

using this approach, for the worst case to happen, all the transactions of a single tree has to fillup the entire memory of the system its sharded o.n which is very unlikely.

Even if that happens we have couple of options.
we can split the tree into subtrees at a depth d or We can split the tree into chains using the heavy light decomposition technique & shard it based on head+chainId or just use a distributed graph database.



